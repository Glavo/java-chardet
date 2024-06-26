/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is mozilla.org code.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1998
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Kohei TAKETA <k-tak@void.in> (Java port)
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.glavo.chardet.prober;

import org.glavo.chardet.DetectedCharset;
import org.glavo.chardet.prober.distributionanalysis.EUCJPDistributionAnalysis;
import org.glavo.chardet.prober.statemachine.CodingStateMachine;
import org.glavo.chardet.prober.statemachine.EUCJPSMModel;
import org.glavo.chardet.prober.statemachine.SMModel;
import org.glavo.chardet.prober.contextanalysis.EUCJPContextAnalysis;

import java.nio.ByteBuffer;

public final class EUCJPProber extends CharsetProber {
    ////////////////////////////////////////////////////////////////
    // fields
    ////////////////////////////////////////////////////////////////
    private final CodingStateMachine codingSM;
    private ProbingState state;

    private final EUCJPContextAnalysis contextAnalyzer;
    private final EUCJPDistributionAnalysis distributionAnalyzer;

    private final ByteBuffer lastChar;

    private static final SMModel smModel = new EUCJPSMModel();


    ////////////////////////////////////////////////////////////////
    // methods
    ////////////////////////////////////////////////////////////////
    public EUCJPProber() {
        super();
        this.codingSM = new CodingStateMachine(smModel);
        this.contextAnalyzer = new EUCJPContextAnalysis();
        this.distributionAnalyzer = new EUCJPDistributionAnalysis();
        this.lastChar = ByteBuffer.allocate(2);
        reset();
    }

    @Override
    public DetectedCharset getCharset() {
        return DetectedCharset.EUC_JP;
    }

    @Override
    public float getConfidence() {
        float contextCf = this.contextAnalyzer.getConfidence();
        float distribCf = this.distributionAnalyzer.getConfidence();

        return Math.max(contextCf, distribCf);
    }

    @Override
    public ProbingState getState() {
        return this.state;
    }

    @Override
    public ProbingState handleData(ByteBuffer buf, int offset, int length) {
        int codingState;

        int maxPos = offset + length;
        for (int i = offset; i < maxPos; ++i) {
            codingState = this.codingSM.nextState(buf.get(i));
            if (codingState == SMModel.ERROR) {
                this.state = ProbingState.NOT_ME;
                break;
            }
            if (codingState == SMModel.ITSME) {
                this.state = ProbingState.FOUND_IT;
                break;
            }
            if (codingState == SMModel.START) {
                int charLen = this.codingSM.getCurrentCharLen();

                if (i == offset) {
                    this.lastChar.put(1, buf.get(offset));
                    this.contextAnalyzer.handleOneChar(this.lastChar, 0, charLen);
                    this.distributionAnalyzer.handleOneChar(this.lastChar, 0, charLen);
                } else {
                    this.contextAnalyzer.handleOneChar(buf, i - 1, charLen);
                    this.distributionAnalyzer.handleOneChar(buf, i - 1, charLen);
                }
            }
        }

        this.lastChar.put(0, buf.get(maxPos - 1));

        if (this.state == ProbingState.DETECTING) {
            if (this.contextAnalyzer.gotEnoughData() && getConfidence() > SHORTCUT_THRESHOLD) {
                this.state = ProbingState.FOUND_IT;
            }
        }

        return this.state;
    }

    @Override
    public final void reset() {
        this.codingSM.reset();
        this.state = ProbingState.DETECTING;
        this.contextAnalyzer.reset();
        this.distributionAnalyzer.reset();
        java.util.Arrays.fill(this.lastChar.array(), (byte) 0);
    }

    @Override
    public void setOption() {
    }
}
