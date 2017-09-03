package bapl.mockito;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
public class RealPartialMock {

	@Test
	public void real_partial_mock() {
		// 通过spy来调用真实的api
		List list = spy(new ArrayList());
		assertEquals(0, list.size());
		A a = mock(A.class);
		// 通过thenCallRealMethod来调用真实的api
		when(a.doSomething(anyInt())).thenCallRealMethod();
		assertEquals(999, a.doSomething(999));
	}
	@Test
    public void reset_mock(){
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        list.add(1);
        assertEquals(10,list.size());
        //重置mock，清除所有的互动和预设
        reset(list);
        assertEquals(0,list.size());
    }
	class A {
		public int doSomething(int i) {
			return i;
		}
	}
}
