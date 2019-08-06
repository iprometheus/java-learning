package com.fangxing.javalearning.jsonlearning;

import com.fangxing.javalearning.mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

//命名空间不要错了：import org.mockito.runners.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {


    //region *************************1.验证行为********************************

    /**
     * 验证行为verify
     */
    @Test
    public void testVerify1() {

        List mockList = mock(List.class);
        mockList.add(1);
        mockList.clear();

        //验证行为
        verify(mockList).add(1);
        verify(mockList).clear();

    }

    /**
     * 验证行为verify
     */
    @Test
    public void testVerify2() {

        List mockList = mock(List.class);
        mockList.add(1);
        mockList.clear();

        //验证行为
        verify(mockList, Mockito.times(1)).add(1);
        verify(mockList).clear();

        //Mockito . verify (mockBean ).someMethod();表示：someMethod方法调用了一次，相当于times(1)
        //Mockito . verify (mock Bean, Mockito.times(n) ).someMethod();表示：someMethod方法调用了n次
        //Mockito . verify (mock Bean, Mockito.never() ).someMethod();表示：someMethod方法未执行
        //Mockito . verify (mock Bean, Mockito. atLeastOnce() ).someMethod();表示：someMethod方法至少执行过一次,相当于atLeast(1)
        //Mockito . verify (mock Bean, Mockito.only() ).someMethod();表示： 仅有someMethod方法执行，且只有一次，不能有其他方法执行
    }

    //endregion

    //region  *************************2.模拟我们所期望的结果********************************

    @Test
    public void whenThenReturnTest() {
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        assertEquals("hello world world", result);
    }

    /**
     * 验证抛出特定类型的异常
     *
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void throwIoExceptionTest() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    /**
     * 验证抛出特定类型的异常
     */
    @Test(expected = RuntimeException.class)
    public void throwRunTimeExceptionTest() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    //endregion

    //region  *************************3.RETURNS_SMART_NULLS********************************

    /**
     * 使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
     */
    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));
        System.out.println(mock.toArray().length);
    }

    //endregion

    //region  *************************4.RETURNS_DEEP_STUBS********************************

    /**
     * RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象
     */
    @Test
    public void deepstubsTest() {
        Account account = mock(Account.class, RETURNS_DEEP_STUBS);
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2() {
        Account account = mock(Account.class);
        RailwayTicket railwayTicket = mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    //endregion

    //region  *************************5.使用注解来快速Mock********************************

    /**
     * 在上面的测试中我们在每个测试方法里都mock了一个List对象，为了避免重复的mock，是测试类更具有可读性，我们可以使用下面的注解方式来快速模拟对象
     * 必须在基类中添加初始化mock的代码
     */
    @Mock
    private List mockList;

//    public MockitoTest() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void annotationsTest() {
        mockList.add(1);
        //验证行为是否发生
        verify(mockList).add(1);
    }

    //endregion

    //region  *************************6.参数匹配********************************

    /**
     * 预设根据不同的参数返回不同的结果
     */
    @Test
    public void argumentsTest() {
        Comparable comparable = mock(Comparable.class);
        //预设根据不同的参数返回不同的结果
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        //对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    /**
     * 除了匹配制定参数外，还可以匹配自己想要的任意参数
     */
    @Test
    public void argumentsAnythingTest() {
        List list = mock(List.class);
        //匹配任意参数
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(!list.contains(3));
    }

    private class IsValid extends ArgumentMatcher<List> {
        @Override
        public boolean matches(Object o) {
            return (int) o == 1 || (int) o == 2;
        }
    }

    /**
     * 如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
     */
    @Test
    public void allArgumentsProvidedByMatchers() {
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao", "hello");
        //如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
        verify(comparator).compare(anyString(), eq("hello"));//这里要求第2个参数一定是hello,使用的是参数匹配eq
        //下面的为无效的参数匹配使用
        //verify(comparator).compare(anyString(),"hello");
    }

    /**
     * 自定义参数匹配
     */
    @Test
    public void argumentMatchersTest() {
        //创建mock对象
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        when(mock.addAll(argThat(new IsListofTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one", "two"));
        //IsListofTwoElements用来匹配size为2的List，因为例子传入List为三个元素，所以此时将失败。
        verify(mock).addAll(argThat(new IsListofTwoElements()));
    }

    /**
     * 自定义参数匹配
     */
    class IsListofTwoElements extends ArgumentMatcher<List> {
        public boolean matches(Object list) {
            //要求列表的长度为2
            return ((List) list).size() == 2;
        }
    }

    //endregion

    //region  *************************7.参数捕获器********************************

    @Test
    public void capturingArgsTest() {
        PersonDao personDao = mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        //声明要捕获person参数
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        personService.update(1, "jack");
        verify(personDao).update(argument.capture());//捕获参数
        assertEquals(1, argument.getValue().getId());//断言
        assertEquals("jack", argument.getValue().getName());
    }

    //endregion

    //region  *************************8.使用方法预期回调接口生成期望值（Answer结构）********************************

    @Mock
    private List mockList2;

    @Test
    public void answerTest() {
        when(mockList2.get(anyInt())).thenAnswer(new CustomAnswer());
        assertEquals("hello world:0", mockList2.get(0));
        assertEquals("hello world:999", mockList2.get(999));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        }
    }

    @Test
    public void answerWithCallbackTest() {
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:" + args[0];
            }
        });
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    /**
     * 修改对未预设的调用返回默认期望
     */
    @Test
    public void unstubbedInvocationsTest() {
        //mock对象使用Answer来对未预设的调用返回默认期望值,针对list的调用都会返回999
        List mock = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });
        //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.get(1));
        //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.size());
    }

    //endregion

    //region  *************************9.用spy监控真实对象********************************

    /**
     * Mock不是真实的对象，它只是用类型的class创建了一个虚拟对象，并可以设置对象行为
     * Spy是一个真实的对象，但它可以设置对象行为
     * InjectMocks创建这个类的对象并自动将标记@Mock、@Spy等注解的属性值注入到这个中
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void spyOnRealObjectsTest() {
        List list = new LinkedList();
        List spy = spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        //when(spy.get(0)).thenReturn(3);

        //预设size()期望值
        when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        assertEquals(100, spy.size());
        assertEquals(1, spy.get(0));
        assertEquals(2, spy.get(1));
        verify(spy).add(1);
        verify(spy).add(2);

        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        doReturn(999).when(spy).get(999);
        assertEquals(999, spy.get(999));
        spy.get(2);//IndexOutOfBoundsException
    }

    /**
     * 真实的部分mock
     */
    @Test
    public void realPartialMockTest() {
        //通过spy来调用真实的api
        List list = spy(new ArrayList());
        assertEquals(0, list.size());
        A a = mock(A.class);
        //通过thenCallRealMethod来调用真实的api
        when(a.doSomething(anyInt())).thenCallRealMethod();
        assertEquals(999, a.doSomething(999));
    }

    class A {
        public int doSomething(int i) {
            return i;
        }
    }

    //endregion

    //region  *************************10.重置mock********************************

    @Test
    public void resetMockTest() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        list.add(1);
        assertEquals(10, list.size());
        //重置mock，清除所有的互动和预设
        reset(list);
        assertEquals(0, list.size());
    }

    //endregion

    //region  *************************11.验证确切的调用次数********************************

    @Test
    public void verifyingNumberOfInvocationsTest(){
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        //验证是否被调用一次，等效于下面的times(1)
        verify(list).add(1);
        verify(list,times(1)).add(1);
        //验证是否被调用2次
        verify(list,times(2)).add(2);
        //验证是否被调用3次
        verify(list,times(3)).add(3);
        //验证是否从未被调用过
        verify(list,never()).add(4);
        //验证至少调用一次
        verify(list,atLeastOnce()).add(1);
        //验证至少调用2次
        verify(list,atLeast(2)).add(2);
        //验证至多调用3次
        verify(list,atMost(3)).add(3);
    }

    //endregion

    //region  *************************12.连续调用********************************

    @Test(expected = RuntimeException.class)
    public void consecutiveCallsTest(){
        //模拟连续调用返回期望值，如果分开，则只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        assertEquals(2,mockList.get(0));
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2,mockList.get(0));
        assertEquals(2,mockList.get(0));
        assertEquals(0,mockList.get(1));
        assertEquals(1,mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }

    //endregion

    //region  *************************13.验证执行顺序********************************

    @Test
    public void verificationInOrderTest(){
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list,list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }

    //endregion

    //region  *************************14.确保模拟对象上无互动发生********************************

    @Test
    public void verifyInteractionTest(){
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);
        list.add(1);
        verify(list).add(1);
        verify(list,never()).add(2);//验证从没有调用add(2)方法
        //验证零互动行为
        verifyZeroInteractions(list2,list3);
    }

    //endregion

    //region  *************************15.找出冗余的互动(即未被验证到的)********************************

    @Test(expected = NoInteractionsWanted.class)
    public void findRedundantInteractionTest(){
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list,times(2)).add(anyInt());
        //检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会通过
        verifyNoMoreInteractions(list);

        List list2 = mock(List.class);
        list2.add(1);
        list2.add(2);
        verify(list2).add(1);
        //检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面的代码会失败抛出异常
        verifyNoMoreInteractions(list2);
    }

    //endregion
}
