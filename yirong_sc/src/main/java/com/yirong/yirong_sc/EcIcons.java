package com.yirong.yirong_sc;

/**
 * Created by yiron on 2018/10/22.
 */

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon{
    icon_test('\ue6ac'),
    icon_sad('\ue77e');

private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
