# Nugget 2D Game Engine

*A lightweight, cross-platform 2D game engine in Java leveraging LWJGL and ImGui Java bindings.*

<p align="left">
  <img src="assets/images/nugget.png" alt="Nugget" width="250"/>
</p>

## 🚀 Features

- **2D Sprite & Tile Rendering**  
  Supports PNG & JPEG via OpenGL.

- **Scene & Entity Management**  
  Runtime creation/destruction, serialization.

- **Input Handling**  
  Keyboard/mouse events with customizable bindings.

- **ImGui Debug Overlay**  
  Real-time property editing and engine statistics.

- **Asset Hot-Reloading**  
  Live updates without restarting the engine.

---

## 🛠️ Getting Started

### Prerequisites

- Java JDK 8 or higher  
- Gradle or Maven

### Installation

```bash
git clone https://github.com/Niketw/nugget.git
cd nugget
./gradlew build  
java -jar build/libs/nugget-1.0-all.jar
```

---

## 📂 Repository Structure

```bash
/ 
├── src/          → Engine source code   
├── build.gradle  → Build script  
├── LICENSE       → MIT License  
└── README.md
```

---

## 🤝 Contributing

1. Fork the repo  
2. Create a feature branch  
```bash git checkout -b feature/foo```
3. Commit your changes  
```bash git commit -am 'Add foo'```
4. Push to your branch  
```bash git push origin feature/foo```
5. Open a Pull Request

---

## ⚖️ License

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for more details.

---

## 🎥 Credits & Acknowledgments

- **[LWJGL](https://www.lwjgl.org/)** – OpenGL bindings for Java  
- **[ImGui Java](https://github.com/SpaiR/imgui-java)** – SpaiR’s Java binding for Dear ImGui

All third-party libraries and assets are properly credited in the LICENSE or respective documentation.

---

Built with ☕ Java and ❤️ open source.

---
