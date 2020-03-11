package stack;

import org.junit.jupiter.api.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifecycleTest {

    private String name;

    public LifecycleTest() {
        System.out.println("constructor");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach");
    }

    @Test
    public void test2() {
        name = "sdsd";
        System.out.println("test2()");
    }

    @Test
    public void test1() {
        System.out.println("test1()");
    }
}
