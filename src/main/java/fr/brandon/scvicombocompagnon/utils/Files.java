/*
 * MIT License
 * 
 * Copyright (c) 2020 Brandon, SCVIComboCompagnon
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.brandon.scvicombocompagnon.utils;

import fr.brandon.scvicombocompagnon.exceptions.FileEmptyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Files
{
    public static final String SC_BINDING_PATH = "resources\\soulcaliburvi\\binding\\";
    public static final String SC_COMBOS_PATH = "resources\\soulcaliburvi\\combos\\";
    public static final String SC_HTML_PATH = "resources\\soulcaliburvi\\html\\";

    private Files()
    {
        throw new IllegalStateException("Utility class");
    }

    public static void checkValidity(String filePath) throws FileNotFoundException, FileEmptyException
    {
        Objects.requireNonNull(filePath, "filePath shouldn't be null");

        if (filePath.isBlank())
        {
            throw new IllegalArgumentException("filePath shouldn't be empty or only contain spaces");
        }
        File file = new File(filePath);

        if (!file.exists())
        {
            throw new FileNotFoundException("File not found");
        }

        if (file.length() <= 0)
        {
            throw new FileEmptyException("File is empty");
        }
    }

    public static Set<String> listFilesInDirectory(String directory)
    {
        return Stream.of(new File(directory).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
                .collect(Collectors.toSet());
    }
}
