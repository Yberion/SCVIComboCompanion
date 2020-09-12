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
package fr.brandon.scvicombocompanion.combo.implementation;

import fr.brandon.scvicombocompanion.binding.implementation.BindingImpl;
import fr.brandon.scvicombocompanion.combo.api.Combo;
import fr.brandon.scvicombocompanion.combo.api.Combos;
import fr.brandon.scvicombocompanion.exceptions.BindingInvalidLineException;
import fr.brandon.scvicombocompanion.exceptions.InvalidHitException;
import fr.brandon.scvicombocompanion.hit.api.Hit;
import fr.brandon.scvicombocompanion.hit.implementation.HitImpl;
import fr.brandon.scvicombocompanion.utils.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class CombosImpl implements Combos
{
    private final Set<Combo> combos;
    private String comboFileName = "combos";

    public CombosImpl()
    {
        this.combos = new LinkedHashSet<>();
    }

    @Override
    public Set<Combo> getCombos()
    {
        return Collections.unmodifiableSet(this.combos);
    }

    @Override
    public String getComboFileName()
    {
        return this.comboFileName;
    }

    @Override
    public void addCombo(Combo combo)
    {
        Objects.requireNonNull(combo);
        this.combos.add(combo);
    }

    @Override
    public void loadCombosFromFile(String fileName) throws IOException, BindingInvalidLineException
    {
        Files.checkValidity(Files.SC_COMBOS_PATH + fileName);
        this.comboFileName = fileName.split("\\.")[0];
        File fileCombos = new File(Files.SC_COMBOS_PATH + fileName);
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileCombos)))
        {

            while ((line = reader.readLine()) != null)
            {
                String[] tmpHitNames = line.split(",");
                Combo tmpCombo = new ComboImpl();

                for (String hitName : tmpHitNames)
                {
                    Hit tmpHit = HitImpl.create(hitName);

                    // HitName starting with { mean text
                    if (!hitName.startsWith("{") && !BindingImpl.getInstance("binding.txt").isHitValid(tmpHit))
                    {
                        throw new InvalidHitException("Invalid hit: " + hitName);
                    }
                    tmpCombo.addHit(tmpHit);
                }
                this.combos.add(tmpCombo);
            }
        }
    }
}
