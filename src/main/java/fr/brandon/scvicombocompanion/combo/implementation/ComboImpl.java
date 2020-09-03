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
package fr.brandon.scvicombocompagnon.combo.implementation;

import fr.brandon.scvicombocompagnon.combo.api.Combo;
import fr.brandon.scvicombocompagnon.hit.api.Hit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ComboImpl implements Combo
{
    private final List<Hit> combo;

    public ComboImpl()
    {
        this.combo = new ArrayList<>();
    }

    @Override
    public List<Hit> getHits()
    {
        return Collections.unmodifiableList(this.combo);
    }

    @Override
    public void addHit(Hit hit)
    {
        Objects.requireNonNull(hit);
        this.combo.add(hit);
    }
}