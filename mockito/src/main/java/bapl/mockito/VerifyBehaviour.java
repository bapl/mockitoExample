package bapl.mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;

public class VerifyBehaviour {

	@Test
    public void verify_behaviour(){
        //模拟创建一个List对象
        List mock = mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
    }
	@Test
    public void verifying_number_of_invocations(){
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
	/**
	 * 连续调用
	 */
	@Test(expected = RuntimeException.class)
    public void consecutive_calls(){
        List mockList= mock(List.class);

		//模拟连续调用返回期望值，如果分开，则只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2,mockList.get(0));
        assertEquals(2,mockList.get(0));
        assertEquals(0,mockList.get(1));
        assertEquals(1,mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }
	/**
	 *  验证执行顺序
	 */
	@Test
    public void verification_in_order(){
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
	/**
	 * 确保模拟对象上无互动发生
	 */
	@Test
    public void verify_interaction(){
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);
        list.add(1);
        verify(list).add(1);
        verify(list,never()).add(2);
        //验证零互动行为
        verifyZeroInteractions(list2,list3);
    }
	@Test(expected = NoInteractionsWanted.class)
    public void find_redundant_interaction(){
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

}
