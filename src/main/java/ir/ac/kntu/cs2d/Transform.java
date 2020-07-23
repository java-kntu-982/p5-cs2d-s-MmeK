package ir.ac.kntu.cs2d;


import ir.ac.kntu.cs2d.Util.Vector2D;

public class Transform {
    private Vector2D position;
    private double rotationZ;

    public Transform(Vector2D position, double rotationZ){
        this.position=position;
        this.rotationZ = rotationZ;
    }
    public Transform(){
        this.position=new Vector2D(0,0);
        this.rotationZ =0;
    }


    public void translate(Vector2D movementVec){
//
//        if()
        position.x+=movementVec.x;
        position.y+=movementVec.y;
    }

    public void rotate(double rotationZ){
        this.rotationZ=rotationZ;
    }


    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public double getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    @Override
    public String toString() {
        return "Transform{" +
                ", position=" + position +
                '}';
    }
}
