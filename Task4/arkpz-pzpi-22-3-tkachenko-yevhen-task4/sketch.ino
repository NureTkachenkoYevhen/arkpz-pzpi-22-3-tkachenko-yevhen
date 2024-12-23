#include <WiFi.h>
#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFiSetup.h>
#include <workout.h>


void setup() {
  Serial.begin(115200);
  setupWiFiConnection();
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    simulatePullUps();
  } else if (WiFi.status() != WL_CONNECTED) {
    Serial.println("You are not connected to WiFi");
  }
}
