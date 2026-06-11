# yaml-api

A basic, standalone, cross-platform YAML processor for Java 17+.

This library was designed to provide a lightweight way to read, manipulate, and save YAML files while preserving comments and formatting, without depending on heavy external libraries like SnakeYAML.

## Installation

### JitPack

To use this library in your project via JitPack:

#### Gradle

Add the JitPack repository to your `build.gradle`:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.dailystruggle:yaml-api:TAG'
}
```
*(Replace `TAG` with a specific release tag or commit hash)*

#### Maven

Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.dailystruggle</groupId>
    <artifactId>yaml-api</artifactId>
    <version>TAG</version>
</dependency>
```

## Features

- **Standalone**: Zero external dependencies.
- **Comment Preservation**: Maintains header and block comments during round-trips.
- **Simple API**: Easy-to-use methods for getting and setting values, similar to common configuration APIs.
- **Cross-Platform**: Works across different environments and platforms.
- **Java 17+**: Optimized for modern Java environments.
- **Automatic Directory Creation**: Automatically creates parent directories when saving files.

## Usage

### Loading a Configuration

```java
File file = new File("config.yml");
YamlConfig config = YamlConfig.load(file);
```

Or parse from a string:

```java
String yaml = "key: value\n# comment\ncount: 5";
YamlConfig config = YamlConfig.parse(yaml);
```

### Reading Values

```java
String value = config.getString("key");
int count = config.getInt("count", 0); // with default value
boolean enabled = config.getBoolean("settings.enabled"); // nested keys supported
```

### Setting Values

```java
config.set("new.key", "new value");
config.set("count", 10);
config.setComment("count", "This is a comment for count");
```

### Saving Configuration

```java
// Save to the file it was loaded from
config.save();

// Or save to a specific file
config.save(new File("output.yml"));

// Or get as a string
String yamlString = config.saveToString();
```

## Project Structure

- `YamlConfig`: The main entry point for loading and saving YAML documents.
- `YamlSection`: Represents a section of a YAML document, providing methods to access and modify data.
- `YamlReader` / `YamlWriter`: Internal classes handling the parsing and emission of YAML content.
- `YamlNode` and subclasses: Represent the internal tree structure of the YAML document.

## Testing

The project uses JUnit 5 for testing. To run the tests, use:

```bash
./gradlew test
```

The tests cover various scenarios, including:
- Comment preservation.
- Deep key access.
- File I/O behavior.
- Parity with simple YAML structures.
