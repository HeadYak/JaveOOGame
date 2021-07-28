package unsw.loopmania.Items.Armor;

public class ShieldDamageBlock extends ArmorBlockDecorator {

    public ShieldDamageBlock(ArmorBlock newArmorBlock) {
        super(newArmorBlock);
    }


    public double getPercentDamageBlocked() {

        double temp = 1.0 - tempBlock.getPercentDamageBlocked();

        return tempBlock.getPercentDamageBlocked() + temp * 0.2;
    }
    
}
