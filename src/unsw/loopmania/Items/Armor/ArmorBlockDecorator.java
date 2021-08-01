package unsw.loopmania.Items.Armor;

public class ArmorBlockDecorator implements ArmorBlock {


    protected ArmorBlock tempBlock;

    public ArmorBlockDecorator(ArmorBlock newArmorBlock){
        tempBlock = newArmorBlock;
    }

    @Override
    public double getPercentDamageBlocked() {
        return tempBlock.getPercentDamageBlocked();
    }
    
}
