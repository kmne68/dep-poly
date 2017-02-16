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
        
        try {
            this.beginningBalance = new double[super.getLifeOfItem()];
            this.endingBalance = new double[super.getLifeOfItem()];
            this.annualDepreciation = new double[super.getLifeOfItem()];
            this.annualRate = new double[super.getLifeOfItem()];
            
            int syd = super.getLifeOfItem() * (super.getLifeOfItem() + 1) / 2;
            double depreciationTotal = super.getAssetCost()- super.getSalvageValue();
            this.beginningBalance[0] = super.getAssetCost();
            
            for(int year = 0; year < super.getLifeOfItem(); year++) {
                if(year > 0) {
                    this.beginningBalance[year] = this.endingBalance[year - 1];
                }
                
                
                int remainingLife = super.getLifeOfItem() - year;   // numerator
                this.annualRate[year] = (double)remainingLife / (double)syd;
                this.annualDepreciation[year] = depreciationTotal * this.annualRate[year];
                this.endingBalance[year] = this.beginningBalance[year] - this.annualDepreciation[year];
            
                this.built = true;
            }
        } catch (Exception e) {
            super.setErrorMessage("SYD build failed" + e.getMessage());
            this.built = false;
        }
    }
    
/*    private void build() {

        if (!super.isValid()) {
            this.built = false;
        } else {
            try {
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];
                this.annualDepreciation = new double[super.getLifeOfItem()];
                
                this.beginningBalance[0] = super.getAssetCost();
                for (int year = 0; year < super.getLifeOfItem(); year++) {

                    if(year > 0) {
                        this.beginningBalance[year] = this.endingBalance[year - 1];
                    }
                    this.annualRate[year + 1] = year + 1 / (((year + 1) * year + 2) / 2);
                    
                    this.annualDepreciation[year] = this.beginningBalance[year] * this.annualRate[year];
                    this.endingBalance[year] = this.beginningBalance[year] * this.annualRate[year];
                }
            } catch (Exception e) {
                
            }
            this.built = true;
        }
    } */

    @Override
    public double getBeginningBalance(int year) {

        if (!this.built) {
            build();
            if (!this.built) {
                return -1;
            }
        }
        return this.beginningBalance[year - 1];
    }

    @Override
    public double getAnnualDepreciation(int year) {

        if (!this.built) {
            build();
            if (!this.built) {
                return -1;
            }
        }
        return this.annualDepreciation[year - 1];
    }

    @Override
    public double getEndingBalance(int year) {
        if (!this.built) {
            build();
            if (!this.built) {
                return -1;
            }
        }
        return this.endingBalance[year - 1];
    }

    public double getAnnualRate(int year) {

        if (!this.built) {
            build();
            if (!this.built) {
                return -1;
            }
        }
        return this.annualRate[year - 1];
    }
}
