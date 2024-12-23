#include "workout.h"
#include <ArduinoJson.h>
#include <math.h>
#include <base64.h>

const String SERVER_URL = "https://rnlbl-31-202-35-207.a.free.pinggy.link/api/metrics";

void generateAccelerometerData(float &ax, float &ay, float &az) {
  ax = random(-1000, 1000) / 1000.0;
  ay = random(-1000, 1000) / 1000.0; 
  az = random(900, 1100) / 1000.0; 
}

void generateUltrasonicData(float &distance) {
  distance = random(200, 900) / 10.0;
}

void processSensorData(float ax, float ay, float az, float distance, float &height, float &tiltAngle) {
  height = distance;

  float magnitude = sqrt(ax * ax + ay * ay + az * az);
  float tiltRadians = acos(az / magnitude);
  tiltAngle = tiltRadians * (180.0 / M_PI);
}

void sendPullUpData(float height, float tiltAngle) {
  HTTPClient http;
  StaticJsonDocument<200> jsonDoc;

  jsonDoc["sessionId"] = 2;
  jsonDoc["height"] = height;
  jsonDoc["tiltAngle"] = tiltAngle;

  String jsonData;
  serializeJson(jsonDoc, jsonData);

  http.begin(SERVER_URL);
  http.addHeader("Content-Type", "application/json");

  String username = "admin";
  String password = "admin";
  String authHeader = "Basic " + base64::encode(username + ":" + password);
  http.addHeader("Authorization", authHeader);

  Serial.println(jsonData);

  int httpResponseCode = http.POST(jsonData);

  if (httpResponseCode > 0) {
    Serial.print("Data sent successfully. Server response code: ");
    Serial.println(httpResponseCode);
  } else {
    Serial.print("Error sending data. Error code: ");
        Serial.print("Failed to send data. Error code: ");
        Serial.print(httpResponseCode);
        Serial.print(" - ");
        Serial.println(http.errorToString(httpResponseCode).c_str());
  }

  http.end();
}

void simulatePullUps() {
  float ax, ay, az;
  float distance;
  float height, tiltAngle;

  generateAccelerometerData(ax, ay, az);
  generateUltrasonicData(distance);

  processSensorData(ax, ay, az, distance, height, tiltAngle);

  sendPullUpData(height, tiltAngle);

  int delayTime = random(1000, 5000);
  delay(delayTime);
}