package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.items.machines.OTMachines;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        OTMaterials.setup(plugin);
        OTMachines.setup(plugin);
    }
}
