/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Keith
 */
abstract public class AssetSYD extends Asset {
    
    private double[] beginningBalance;
    private double[] annualDepreciation;
    private double[] endingBalance;
    private double[] annualRate;
    private boolean built;
    
    public static final double FACTOR = 2.0;
    
    public AssetSYD() {
        super();   
        this.built = false;
    }

    public AssetSYD(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life);        
        
        if (super.isValid()) {
            build();
        }
    }
    
    
    private void build() {
        // remove this loop for program
        for ( int i = 0; i < super.getLifeOfItem(); i++) {
            this.beginningBalance[i] = Math.random() * 100.0;
            this.annualDepreciation[i] = Math.random() * 100.0;
            this.endingBalance[i] = Math.random() * 100.0;
            this.annualRate[i] = Math.random() * 100.0;
        }
    }
    
}