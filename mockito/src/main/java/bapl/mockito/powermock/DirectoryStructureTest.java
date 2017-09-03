package bapl.mockito.powermock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner; 

@RunWith(PowerMockRunner.class) 
@PrepareForTest(DirectoryStructure.class) 
public class DirectoryStructureTest { 
   @Test 
   public void createDirectoryStructureWhenPathDoesntExist() throws Exception { 
       final String directoryPath = "mocked path"; 
 
       File directoryMock = mock(File.class); 
 
       // This is how you tell PowerMockito to mock construction of a new File. 
       whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock); 
 
       // Standard expectations 
       when(directoryMock.exists()).thenReturn(false); 
       when(directoryMock.mkdirs()).thenReturn(true); 
 
       assertTrue(new NewFileExample().createDirectoryStructure(directoryPath)); 
 
       // Optionally verify that a new File was "created". 
       verifyNew(File.class).withArguments(directoryPath); 
   } 
}