#ifndef WIFISETUP_H
#define WIFISETUP_H

#include <WiFi.h>

extern const char* WIFI_SSID;      
extern const char* WIFI_PASSWORD;  

void setupWiFiConnection();

#endif 