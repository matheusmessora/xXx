package br.com.pandox.xxx.dto.enums;

/**
 * Created by MatheusPC on 14/11/2014.
 */
public enum MapEnum {


    LORENCIA(0, "Lorencia"),
    DUNGEON(1, "Dungeon"),
    DEVIAS(2, "Devias"),
    Noria(3, "Noria"),
    LOST_TOWER(4, "Lost Tower"),
    ATLANS(7, "Atlans"),
    TARKAN(8, "Tarkan"),
    ICARUS(10, "Icarus");


    private int id;
    private String mapName;

    MapEnum(int id, String mapName) {
        this.mapName = mapName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public static MapEnum valueOf(int id) {
        for (MapEnum map : MapEnum.values()) {
            if (map.id == id) {
                return map;
            }
        }

        return MapEnum.LORENCIA;
    }
}
