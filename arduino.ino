
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>


#define HOST "alcohol-detection.firebaseio.com"
#define AUTH "bvnNr8tYQub6cg6biCCA4NQhoB9DC6Fnhqr7rDLD"
#define ssid "Insecure Channel"
#define pass "divyansh14474"

void setup() {
  Serial.begin(115200);
  pinMode(A0,INPUT);
  // connect to wifi.
  WiFi.begin(ssid, pass);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(HOST,AUTH);
}

int n=0;
void loop() 
{
  float temp = analogRead(A0);
  temp = map(temp,80,100,0,3);
  Serial.println(temp);
  // set value
  Firebase.setFloat("number", temp);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);
  
  // remove value
  Firebase.remove("number");
  delay(100);

  
  
  // append a new value to /logs
  String name = Firebase.pushInt("logs", n++);
  // handle error
  if (Firebase.failed()) {
      Serial.print("pushing /logs failed:");
      Serial.println(Firebase.error());  
      return;
  }

}
