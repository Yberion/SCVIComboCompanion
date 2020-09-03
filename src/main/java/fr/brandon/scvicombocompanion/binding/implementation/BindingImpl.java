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
package fr.brandon.scvicombocompagnon.binding.implementation;

import fr.brandon.scvicombocompagnon.binding.api.Binding;
import fr.brandon.scvicombocompagnon.exceptions.BindingInvalidLineException;
import fr.brandon.scvicombocompagnon.hit.api.Hit;
import fr.brandon.scvicombocompagnon.hit.api.HitImage;
import fr.brandon.scvicombocompagnon.hit.implementation.*;
import fr.brandon.scvicombocompagnon.utils.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class BindingImpl implements Binding
{
    private static BindingImpl instance;
    private final Map<Hit, HitImage> binding;

    private BindingImpl(String fileName) throws IOException, BindingInvalidLineException
    {
        this.binding = new HashMap<>();
        File fileToParse = new File(Files.SC_BINDING_PATH + fileName);
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToParse)))
        {

            while ((line = reader.readLine()) != null)
            {

                if (!line.contains("="))
                {
                    throw new BindingInvalidLineException("The line might contain \"=\"");
                }
                // [0] = image name
                // [1] = hit name
                String[] splittedLine = line.split("=");
                this.binding.put(HitImpl.create(splittedLine[1]), HitImageImpl.create(splittedLine[0]));
            }
        }
    }

    // TODO: Rework, what if we load a binding and want to load another one later ? (answer: we can't right now)
    public static synchronized BindingImpl getInstance(String fileName) throws IOException, BindingInvalidLineException
    {
        Files.checkValidity(Files.SC_BINDING_PATH + fileName);

        if (instance == null)
        {
            instance = new BindingImpl(fileName);
        }
        return instance;
    }

    @Override
    public HitImage getImageFromHit(Hit hit)
    {
        return this.binding.get(hit);
    }

    @Override
    public boolean isHitValid(Hit hit)
    {
        return this.binding.containsKey(hit);
    }
}
