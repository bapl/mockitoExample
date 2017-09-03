package bapl.mockito;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * 2.11 用spy监控真实对象 Mock不是真实的对象， 它只是用类型的class创建了一个虚拟对象，并可以设置对象行为 Spy是一个真实的对象，
 * 但它可以设置对象行为 InjectMocks创建这个类的对象并自动将标记
 * 
 * @Mock、@Spy等注解的属性值注入到这个中
 *
 * 
 */
public class SpyTest {
	@Test(expected = IndexOutOfBoundsException.class)
	public void spy_on_real_objects() {
		List list = new LinkedList();
		List spy = spy(list);
		// 下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
		// when(spy.get(0)).thenReturn(3);

		// 使用doReturn-when可以避免when-thenReturn调用真实对象api
		doReturn(999).when(spy).get(999);
		// 预设size()期望值
		when(spy.size()).thenReturn(100);
		// 调用真实对象的api
		spy.add(1);
		spy.add(2);
		assertEquals(100, spy.size());
		assertEquals(1, spy.get(0));
		assertEquals(2, spy.get(1));
		verify(spy).add(1);
		verify(spy).add(2);
		assertEquals(999, spy.get(999));
		spy.get(2);
	}
}
