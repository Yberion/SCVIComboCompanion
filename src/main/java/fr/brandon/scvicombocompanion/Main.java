/*
 * MIT License
 * 
 * Copyright (c) 2020 Brandon, SCVIComboCompanion
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
package fr.brandon.scvicombocompanion;

import fr.brandon.scvicombocompanion.combo.api.Combos;
import fr.brandon.scvicombocompanion.combo.implementation.CombosImpl;
import fr.brandon.scvicombocompanion.exceptions.BindingInvalidLineException;
import fr.brandon.scvicombocompanion.html.api.Html;
import fr.brandon.scvicombocompanion.html.impl.HtmlImpl;
import fr.brandon.scvicombocompanion.utils.Files;
import java.io.IOException;

public class Main
{
    public static void main(String... args) throws IOException, BindingInvalidLineException
    {
        Html html = new HtmlImpl();

        for (String file : Files.listFilesInDirectory(Files.SC_COMBOS_PATH))
        {
            Combos combos = new CombosImpl();
            combos.loadCombosFromFile(file);
            html.make(combos);
        }
    }
}
