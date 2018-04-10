package ch.texelengine.math.linearalgebra;

public final class TestMain {

    public static void main(String[] args) {
        Matrix4f m = new Matrix4f();
        Vector3f v = new Vector3f(0, 1, 0);
        m.translationRotationScale(new Vector3f(0, 0, 2), new Quaternionf(new Vector3f(1, 0, 0), 90), new Vector3f(1, 2, 1));
        Matrix4f inv = new Matrix4f();
        m.inverse(inv);
        System.out.println(v.transform(m).transform(inv));

    }

}
