package unsw.loopmania.Items.Armor;

public class HelmetDamageBlock extends ArmorBlockDecorator {

    public HelmetDamageBlock(ArmorBlock newArmorBlock) {
        super(newArmorBlock);
    }
    
    public double getPercentDamageBlocked() {

        double temp = 1.0 - tempBlock.getPercentDamageBlocked();

        return tempBlock.getPercentDamageBlocked() + temp * 0.1;
    }

}
