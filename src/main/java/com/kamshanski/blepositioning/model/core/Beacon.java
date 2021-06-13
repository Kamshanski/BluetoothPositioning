package com.kamshanski.blepositioning.model.core;

public enum Beacon {
    MAIN(
            "24:6f:28:b2:dc:7a".replaceAll(":", "").toLowerCase(),
            37, 0.0, 0.0, 0.0),
    SLAVE_1(
            "a4:cf:12:8d:75:c2".replaceAll(":", "").toLowerCase(),
            41, 0.0, 70.0, 0.0),
    SLAVE_2(
            "30:ae:a4:8b:44:2a".replaceAll(":", "").toLowerCase(),
            42, 70.0, 70.0, 0.0),
    SLAVE_3(
            "a4:cf:12:8d:40:be".replaceAll(":", "").toLowerCase(),
            43, 70.0, 00.0, 0.0),
    TARGET(
            "B4:E6:2D:C1:E5:07".replaceAll(":", "").toLowerCase(),
            -1,  -1, -1, -1);

    public final MacAddress mac;
    public final int id;
    public final double x, y, z;

    Beacon(String mac, int id, double x, double y, double z) {
        this.mac = MacAddress.getMac(mac);
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Beacon byId(int id) {
        Beacon[] beacons = values();
        for (Beacon b : beacons) {
            if (b.id == id) {
                return b;
            }
        }
        return null;
    }

}
