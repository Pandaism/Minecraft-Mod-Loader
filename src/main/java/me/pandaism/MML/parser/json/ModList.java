package me.pandaism.MML.parser.json;

import java.util.Set;

/**
 * @author Pandaism
 * @date 1/30/2019 : 5:30 PM
 */
public class ModList {
    private Set<String> mods;

    public ModList(Set<String> mods) {
        this.mods = mods;
    }

    public Set<String> getMods() {
        return mods;
    }

    public void setMods(Set<String> mod) {
        this.mods = mod;
    }
}
