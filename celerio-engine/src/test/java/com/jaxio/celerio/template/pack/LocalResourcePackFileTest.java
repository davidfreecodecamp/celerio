/*
 * Copyright 2015 JAXIO http://www.jaxio.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaxio.celerio.template.pack;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class LocalResourcePackFileTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullFolder() {
        new LocalResourcePackFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownFolder() {
        new LocalResourcePackFile("");
    }

    // @Test
    // public void folderWithNoFile() {
    // TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/0-file/");
    // Assert.assertEquals(0, pack.getTemplateNames().size());
    // }

    @Test
    public void folderWithOneFile() {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/1-file/");
        assertThat(pack.getTemplateNames()).hasSize(1);
    }

    @Test
    public void folderWithTwoFiles() {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/2-files/");
        assertThat(pack.getTemplateNames()).hasSize(2);
    }

    @Test
    public void folderWithTwoFilesAndABakFile() {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/2-files-and-1-bak-file/");
        assertThat(pack.getTemplateNames()).hasSize(2);
    }

    @Test
    public void folderWithThreeFilesAndSubFolder() {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/3-files-subfolder/");
        assertThat(pack.getTemplateNames()).hasSize(3);
    }

    @Test
    public void copyResource() throws IOException {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/3-files-subfolder/");
        assertThat(pack.getTemplateNames()).hasSize(3);
        assertThat(pack.getTemplateByName("two.txt").getTemplate()).isEqualTo("two-expected");
        assertThat(pack.getTemplateByName("subfolder/three.txt").getTemplate()).isEqualTo("three-expected");
    }

    @Test(expected = IllegalArgumentException.class)
    public void templateNotInFileList() throws IOException {
        TemplatePack pack = new LocalResourcePackFile("src/test/resources/templates/3-files-subfolder/");
        pack.getTemplateByName("unkown.txt");
    }
}