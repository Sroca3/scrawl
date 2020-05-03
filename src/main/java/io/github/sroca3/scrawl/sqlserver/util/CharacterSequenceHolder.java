package io.github.sroca3.scrawl.sqlserver.util;

import java.util.Objects;

public abstract class CharacterSequenceHolder implements CharSequence {

    protected CharSequence characterSequence;

    public CharacterSequenceHolder(CharSequence characterSequence) {
        Objects.requireNonNull(characterSequence);
        this.characterSequence = characterSequence;
    }

    @Override
    public int length() {
        return this.characterSequence.length();
    }

    @Override
    public char charAt(int i) {
        return this.characterSequence.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.characterSequence.subSequence(i, i1);
    }
}