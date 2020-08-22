import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.MessageInputStateless;
import com.ibm.watson.assistant.v2.model.MessageResponseStateless;
import com.ibm.watson.assistant.v2.model.MessageStatelessOptions;

public class ChatBotTest {
	public static void main(String[] args) {
		IamAuthenticator authenticator = new IamAuthenticator("WCBcG3f0KEjcIpyr-T98YBz1jf5Yq38MFf_tdkJkYN8x");
		Assistant assistant = new Assistant("2020-04-01", authenticator);
		assistant.setServiceUrl("https://api.us-south.assistant.watson.cloud.ibm.com/instances/e4253e7f-0dc0-46df-9898-530230874444");

		HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
				  .disableSslVerification(true)
				  .build();
				assistant.configureClient(configOptions);
				
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Watson-Learning-Opt-Out", "true");

		assistant.setDefaultHeaders(headers);
		
	//	String workspaceId = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/e4253e7f-0dc0-46df-9898-530230874444/v1/workspaces/d17624de-8b6d-4cde-be55-a611d773255d/message";
		
		//CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder("test").build();
		
		//SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute().getResult();
		
		//InputData input = new InputData.Builder("사용법").build();

		
//		MessageInput input = new MessageInput.Builder()
//				  .messageType("text")
//				  .text("Hello")
//				  .build();
		
		/*
		 * MessageOptions options = new MessageOptions.Builder(workspaceId)
		 * .input(input) .build();
		 */
		Scanner sc = new Scanner(System.in);
			try {
				// welcome 뽑기
				MessageInputStateless input1 = new MessageInputStateless.Builder().build();
				MessageStatelessOptions options1 = new MessageStatelessOptions.Builder()
						.assistantId("97206189-5af4-4241-8449-a3d32510ee31")
						.input(input1)
						.build();
				MessageResponseStateless response1 = assistant.messageStateless(options1)
						.execute()
						.getResult();
				//System.out.println(response1);
				JSONParser welcomeJsonParse = new JSONParser();
				
				JSONObject welcomeJsonObject = (JSONObject) welcomeJsonParse.parse(response1.toString());
				JSONObject welcomeOutputObj = (JSONObject) welcomeJsonObject.get("output");
				JSONArray welcomeJa = (JSONArray) welcomeOutputObj.get("generic");
				//System.out.println(ja.toString());
				//System.out.println(outputObj);
				
				JSONObject welcomeResult = (JSONObject) welcomeJa.get(0);
				System.out.println((String) welcomeResult.get("text"));
				
				/////////////////////////
				do {
				
				
				System.out.print("입력 -> ");
				String text = sc.nextLine();
				MessageInputStateless input = new MessageInputStateless.Builder()
						  .messageType("text")
						  .text(text)
						  .build();
				MessageStatelessOptions options = new MessageStatelessOptions.Builder()
						  .assistantId("97206189-5af4-4241-8449-a3d32510ee31")
						  .input(input)
						  .build();
		
				MessageResponseStateless response = assistant.messageStateless(options)
				  .execute()
				  .getResult();
				//MessageResponse response = service.message(options).execute();
				
				//System.out.println(response);
				JSONParser jsonParse = new JSONParser();
				
				JSONObject jsonObject = (JSONObject) jsonParse.parse(response.toString());
				JSONObject outputObj = (JSONObject) jsonObject.get("output");
				JSONArray ja = (JSONArray) outputObj.get("generic");
				//System.out.println(ja.toString());
				//System.out.println(outputObj);
				
				JSONObject result = (JSONObject) ja.get(0);
				System.out.println(result.get("text"));
				
				}while(true);		
					
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		
		
	}
}
