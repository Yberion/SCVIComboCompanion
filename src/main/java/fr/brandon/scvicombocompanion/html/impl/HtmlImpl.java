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
package fr.brandon.scvicombocompanion.html.impl;

import fr.brandon.scvicombocompanion.binding.implementation.BindingImpl;
import fr.brandon.scvicombocompanion.combo.api.Combo;
import fr.brandon.scvicombocompanion.combo.api.Combos;
import fr.brandon.scvicombocompanion.exceptions.BindingInvalidLineException;
import fr.brandon.scvicombocompanion.hit.api.Hit;
import fr.brandon.scvicombocompanion.html.api.Html;
import fr.brandon.scvicombocompanion.utils.Files;
import fr.brandon.scvicombocompanion.utils.Strings;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class HtmlImpl implements Html
{
    @Override
    public void make(Combos combos) throws IOException, BindingInvalidLineException
    {
        StringBuilder htmlFormat = new StringBuilder();
        File htmlCombos = new File(Files.SC_HTML_PATH + combos.getComboFileName() + ".html");

        if (!htmlCombos.exists())
        {
            htmlCombos.createNewFile();
        }
        htmlFormat.append("<!doctype html>\r\n" + "<html lang=\"fr\">\r\n" + "\r\n" + "<head>\r\n"
                + "    <meta charset=\"utf-8\">\r\n" + "    <title>SoulCalibur VI Combo Compagnon - "
                + combos.getComboFileName() + "</title>\r\n"
                + "    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
                + "    <h1>SoulCalibur VI Combo Compagnon - " + combos.getComboFileName() + "</h1>\n");

        for (Combo combo : combos.getCombos())
        {
            htmlFormat.append("    <div class=\"combo\">\n");

            for (Hit hit : combo.getHits())
            {

                // Special case for text
                if (hit.getName().startsWith("{"))
                {
                    htmlFormat.append("        <span class=\"combo_text combo_valign_center\">"
                            + hit.getName().split("\\{|\\}")[1] + "</span>\r\n");
                }
                else
                {
                    String hitImageName = BindingImpl.getInstance("binding.txt").getImageFromHit(hit).getName();

                    if (Strings.isNumeric(hitImageName))
                    {
                        htmlFormat.append("        <img src=\"images/" + hitImageName + ".png\" alt=\"" + hit.getName()
                                + "\">\r\n");
                    }
                    else if (hitImageName.equals("space"))
                    {
                        htmlFormat.append("        <span class=\"combo_space\"></span>\r\n");
                    }
                    else if (hitImageName.equals("woh") || hitImageName.equals("roh") || hitImageName.equals("sky"))
                    {
                        htmlFormat.append(
                                "        <span class=\"combo_special_hit combo_special_hit_wohrohsky combo_valign_center\">"
                                        + hit.getName().split("\\(|\\)")[1] + "</span>\r\n");
                    }
                    else
                    {
                        htmlFormat.append("        <span class=\"combo_special_hit combo_special_hit_" + hitImageName
                                + " combo_valign_center\">" + hit.getName().split("\\(|\\)")[1] + "</span>\r\n");
                    }
                }
            }
            htmlFormat.append("    </div>\r\n");
        }
        htmlFormat.append("</body>\r\n" + "\r\n" + "</html>");
        Writer targetFileWriter = new FileWriter(htmlCombos);
        targetFileWriter.write(htmlFormat.toString());
        targetFileWriter.close();
    }
}
