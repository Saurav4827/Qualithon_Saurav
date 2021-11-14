package QT;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import org.glassfish.tyrus.client.ClientManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

@ClientEndpoint
public class Bot_TreasureSolver {
	public static WebDriver driver;
	public static String socketmsg;
	public static String Rcvdmsg;
	Bot_TreasureHelper btHelper = new Bot_TreasureHelper();

	public void LaunchQualithonWebApp() {
		 DesiredCapabilities caps = new DesiredCapabilities();
	        LoggingPreferences logPrefs = new LoggingPreferences();
	        logPrefs.enable(LogType.BROWSER, Level.ALL);
	        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");

		// Instantiate a ChromeDriver class.
		driver = new ChromeDriver(caps);

		// Launch Website
		driver.navigate().to("http://54.80.137.197:5000/");

		// Maximize the browser
		driver.manage().window().maximize();
	}

	public WebElement element(String data) throws IOException {

		Yaml y = new Yaml();
		WebElement element = null;
		FileReader reader1 = new FileReader("src/test/resources/PageSpecs/locator.yml");
		Map<String, Map<String, String>> obj = y.load(reader1);
		String locator = obj.get(data).get("locator");
		System.out.println(locator);
		String by = obj.get(data).get("By");
		System.out.println(by);
		if (by.contains("xpath")) {
			element = driver.findElement(By.xpath(locator));
		} else if (by.contains("id")) {
			element = driver.findElement(By.id(locator));
		} else if (by.contains("css")) {
			element = driver.findElement(By.cssSelector(locator));
		}

		return element;
	}

	public List<WebElement> elements(String data) throws IOException {

		Yaml y = new Yaml();
		List<WebElement> elements = null;
		FileReader reader1 = new FileReader("src/test/resources/PageSpecs/locator.yml");
		Map<String, Map<String, String>> obj = y.load(reader1);
		String locator = obj.get(data).get("locator");
		System.out.println(locator);
		String by = obj.get(data).get("By");
		System.out.println(by);
		if (by.contains("xpath")) {
			elements = driver.findElements(By.xpath(locator));
		} else if (by.contains("id")) {
			elements = driver.findElements(By.id(locator));
		} else if (by.contains("css")) {
			elements = driver.findElements(By.cssSelector(locator));
		}

		return elements;
	}

	public WebElement element(String data, String replace) throws IOException {

		Yaml y = new Yaml();
		WebElement element = null;
		FileReader reader1 = new FileReader("src/test/resources/PageSpecs/locator.yml");
		Map<String, Map<String, String>> obj = y.load(reader1);
		String locator = obj.get(data).get("locator");
		locator = locator.replaceAll("\\$\\{.+\\}", replace);
		System.out.println(locator);
		String by = obj.get(data).get("By");
		System.out.println(by);
		if (by.contains("xpath")) {
			element = driver.findElement(By.xpath(locator));
		} else if (by.contains("id")) {
			element = driver.findElement(By.id(locator));
		} else if (by.contains("css")) {
			element = driver.findElement(By.cssSelector(locator));
		}

		return element;
	}

	public void EnterToMaP() throws IOException {
		element("EnterDoor").click();

	}

	public void StartTresure() throws IOException {
		element("StartTreasure").click();

	}

	public void C1_Proceed() throws IOException, InterruptedException {

		List<WebElement> Proceed_buttons = elements("C1_Proceed");
		for (WebElement ele : Proceed_buttons) {
			ele.click();
			if (element("C2_VideoTitle").getText().trim().contentEquals("A Video Player")) {
				System.out.println(driver.getCurrentUrl());
				System.out.println(driver.getTitle());
				break;
			}
		}

	}

	public void C2_PlayVideo() throws IOException, InterruptedException {

		driver.switchTo().frame("aVideoPlayer");
		Actions actions = new Actions(driver);
		element("Play_Norm").click();
		btHelper.Wait(10);
		actions.moveToElement(element("Video"));
		actions.perform();
		element("Mute_Norm").click();
		btHelper.Wait(2);
		driver.switchTo().parentFrame();
		element("C1_Proceed").click();

	}

	public void C3_CrystalMaze() throws IOException {
		try {

			element("CrystalMaze_y8");

			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("Arrowupward").click();
			element("Arrowupward").click();
			element("Arrowupward").click();
			element("ArrowBack").click();
			element("ArrowBack").click();
			element("Arrowupward").click();
			element("Arrowupward").click();
			element("ArrowForward").click();
			element("Arrowupward").click();
			element("Arrowupward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
		}

		catch (Exception e) {
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("Arrowupward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowBack").click();
			element("ArrowDown").click();
			element("ArrowBack").click();
			element("ArrowBack").click();
			element("ArrowBack").click();
			element("ArrowDown").click();
			element("ArrowBack").click();
			element("ArrowDown").click();
			element("ArrowDown").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();
			element("Arrowupward").click();
			element("Arrowupward").click();
			element("ArrowForward").click();
			element("Arrowupward").click();
			element("ArrowForward").click();
			element("ArrowForward").click();

		}

		// submit crystal_maze
		element("CrystalMazeSubmit").click();
	}

	public void Maps() throws InterruptedException, AWTException, IOException {
		btHelper.Wait(10);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);
		btHelper.Wait(2);
		for (int i = 0; i < 37; i++) {
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
		}
		for (int i = 0; i < 10; i++) {
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
		}
		element("C1_Proceed").click();

	}
	
	public void Captcha() throws WebDriverException, IOException
	{
//		File captchaImg = element("Captcha").getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir")+"/src/test/resources/Screenshot/captcha.png";
//		FileHandler.copy(captchaImg, new File(path));
//		
//		ITesseract tess = new Tesseract();
//		tess.setDatapath("src/test/resources/Tess4J/tessdata");
//		String Text = tess.doOCR(new File(path));
		String captchaCode =null;
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            captchaCode = entry.getMessage();
        }
        System.out.println(captchaCode.split("\"")[1]);
        element("CaptchaText").sendKeys(captchaCode.split("\"")[1]);
        element("SubmitCaptcha").click();
	}
	
	@OnOpen
	  public void onOpen(Session session) {
	    System.out.println ("--- Connected " + session.getId());
	    try {
	    	System.out.println(element("SocketMessage").getText().trim());
	      session.getBasicRemote().sendText(element("SocketMessage").getText().trim());
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
		
	  @OnMessage
	  public String onMessage(String message, Session session) throws InterruptedException {
	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    try {
	      set_msg(message);
	      System.out.println ("--- Received " + message);
	      try{    
	           FileWriter fw=new FileWriter("src/test/resources/data.txt");    
	           fw.write(message);    
	           fw.close();    
	          }catch(Exception e){System.out.println(e);}    
	          System.out.println("Success...");    
	       
	      String userInput = bufferRead.readLine();
	      return userInput;
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  @OnClose
	  public void onClose(Session session, CloseReason closeReason) {
	    System.out.println("--- Session: " + session.getId());
	    System.out.println("--- Closing because: " + closeReason);
	  }
	  
	  public void set_msg(String Rcvdmsg)
	  {
		  this.Rcvdmsg=Rcvdmsg;
	  }
	  

	public void WebSocket() throws IOException, jakarta.websocket.DeploymentException, InterruptedException {
		 ClientManager client = ClientManager.createClient();
		    try {
		      URI uri = new URI("ws://54.80.137.197:5001");
		      client.connectToServer(Bot_TreasureSolver.class, uri);

		    } catch (URISyntaxException e) {
		      e.printStackTrace();
		    }
		    
		 
	}

	public String Submit() throws IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data.txt"));
	    String currentLine = reader.readLine();
	    reader.close();
	
		element("SocketSubmit").sendKeys(currentLine);
		element("SocketSubmit").clear();
		reader = new BufferedReader(new FileReader("src/test/resources/data.txt"));
		currentLine = reader.readLine();
	    reader.close();
		element("SocketSubmit").sendKeys(currentLine);
		element("ScketbuttonSubmit").click();
		return element("Thanks").getText();
	}
	
	

}
