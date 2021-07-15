package unsw.loopmania.Items;

public interface BlockBehaviour {
    double getDamageTaken(double damageTaken);
}


class noDamageBlock implements BlockBehaviour{

    @Override
    public double getDamageTaken(double damageTaken) {
        // TODO Auto-generated method stub
        return damageTaken;
    }

}

class ChestArmorDamageBlock implements BlockBehaviour {

    @Override
    public double getDamageTaken(double damageTaken) {
        // TODO Auto-generated method stub
        return damageTaken*0.5;
    }

}

class HelmetDamageBlock implements BlockBehaviour {

    @Override
    public double getDamageTaken(double damageTaken) {
        return damageTaken*0.9;
    }
    
}
