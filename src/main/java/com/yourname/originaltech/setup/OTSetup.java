package com.yourname.originaltech.setup;

import com.yourname.originaltech.OriginalTech;
import com.yourname.originaltech.items.materials.OTMaterials;
import com.yourname.originaltech.items.machines.OTMachines;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        OTMaterials.setup(plugin);
        OTMachines.setup(plugin);
    }
}
