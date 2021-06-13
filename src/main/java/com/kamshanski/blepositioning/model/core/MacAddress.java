package com.kamshanski.blepositioning.model.core;

import java.util.HashMap;
import java.util.Objects;

public class MacAddress {

    public static final HashMap<String, MacAddress> macs = new HashMap<>(10);
    public String macString;

    public MacAddress(String macString) {
        this.macString = macString.replaceAll(":", "").toLowerCase();
    }

    byte[] toByteArray() {
        byte[] b = new byte[6];

        for (int j = 0; j < 6; j++) {
            String hex = macString.substring(j*3, (j*3)+1);
            b[j] = Byte.parseByte(hex, 16);
        }

        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MacAddress)) return false;
        return macString.equals(((MacAddress) o).macString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(macString);
    }

    // helps to keep only one instance of MacAddress per device
    public static MacAddress getMac(String macStr) {
        return macs.computeIfAbsent(macStr, MacAddress::new);
    }
}
