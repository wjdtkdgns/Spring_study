package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        System.out.println(a == b);

        Address address = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");
        System.out.println(address2==address);
    }
}
