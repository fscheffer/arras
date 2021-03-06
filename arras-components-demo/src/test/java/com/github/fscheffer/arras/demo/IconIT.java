// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.fscheffer.arras.demo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.fscheffer.arras.test.ArrasTestCase;
import com.github.fscheffer.arras.test.CssMatrix;

public class IconIT extends ArrasTestCase {

    @BeforeMethod
    void before() {
        open("/IconDemo");
    }

    @Test
    public void testIcons() {

        waitUntil(visible("#simple"));

        int baseSize = 14;

        List<WebElement> sizes = elements("#sizes > i");
        assertFontSize(sizes.get(0), baseSize);
        assertFontSize(sizes.get(1), baseSize * 2);
        assertFontSize(sizes.get(2), baseSize * 3);
        assertFontSize(sizes.get(3), baseSize * 4);
        assertFontSize(sizes.get(4), baseSize * 5);

        // effects
        assertTransformation("#normal > i", "none");
        assertTransformation("#rotate90 > i", "matrix(0, 1, -1, 0, 0, 0)");
        assertTransformation("#rotate180 > i", "matrix(-1, 0, 0, -1, 0, 0)");
        assertTransformation("#rotate270 > i", "matrix(0, -1, 1, 0, 0, 0)");

        assertTransformation("#flipHorizontal > i", "matrix(-1, 0, 0, 1, 0, 0)");
        assertTransformation("#flipVertical > i", "matrix(1, 0, 0, -1, 0, 0)");

    }

    private void assertFontSize(WebElement element, Integer expected) {
        Assert.assertEquals(element.getCssValue("font-size"), expected.toString() + "px");
    }

    private void assertTransformation(String cssSelector, String expected) {

        String transform = css(cssSelector, "transform");

        if (!expected.equals(transform)) {
            Assert.assertEquals(CssMatrix.fromString(transform), CssMatrix.fromString(expected));
        }
    }
}
