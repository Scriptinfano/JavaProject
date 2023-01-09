class TestMain {
    private Boolean mark;
    private int x;

    public TestMain(Boolean mark, int x) {
        this.mark = mark;
        this.x = x;
    }

    public void showMark() {
        System.out.println(mark);
    }

    public boolean getMark() {
        return mark;
    }

    public void fun(TestMain main) {
        x = main.x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}

class TestMain2 {
    public static void main(String[] args) {
        TestMain main1 = new TestMain(true, 12);
        TestMain main2 = new TestMain(false, 14);
        main1.fun(main2);
        System.out.println(main1.getX());
    }
}