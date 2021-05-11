#include "BluetoothSerial.h"

#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial SerialBT;

void callback(esp_spp_cb_event_t event, esp_spp_cb_param_t *param){
  if(event == ESP_SPP_SRV_OPEN_EVT){
    Serial.println("Client connected");
  }
  if(event == ESP_SPP_CLOSE_EVT ){
    Serial.println("Client disconnected");
  }
}

void setup() {
  Serial.begin(115200);

  SerialBT.register_callback(callback);

  if (!SerialBT.begin("ESP32_board")) {
    Serial.println("An error occurred initializing Bluetooth");
  }
  else {
    Serial.println("Bluetooth initialized");
  }
}

void loop() {
  const int buffer_size = 64;
  uint8_t buffer[buffer_size];
  int pos = 0;
  while (SerialBT.available() > 0) {
    int input = SerialBT.read();
    Serial.write(input);
    buffer[pos] = input;
    pos++;
  }
  SerialBT.write(buffer, pos);
  delay(50);
}
