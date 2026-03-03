package com.lukeonuke.fastleafdecay.service;

import org.bukkit.block.Block;

public class TaxicabDistanceService {
    public static int distance(Block b1, Block b2){
        return Math.abs(b1.getX() - b2.getY())
                    + Math.abs(b1.getY() - b2.getY())
                    + Math.abs(b1.getZ() - b2.getZ());
    }
}
