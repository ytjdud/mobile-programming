# Simple Online-Shopping Application
Mobile Programming - Fall 2022 semester at Kookmin University

## Development Environment  
Android Studio @2021.2.1 Patch 2

## Application Version
- complieSdkVersion : 32
- targetSdkVersion : 32
- minSdkVersion : 30
- buildFeatures { viewBinding true } 추가

## Views
### App Access Page
- use Relative Layout

### Sign-Up Page
- Use Linear Layout
- Utilize regular expressions for checking ID duplication and password length/special character rules.
- Use Preferences to store user information.

### Product Image List with Product Name Page
- Use Constraint Layout.
- If the user is **logged in** and clicks the "Sign-Up Information" button,     
  it is directed to an additional screen that displays their registration information.
- If the user is **not logged in** and clicks the "Sign-Up Information" button,    
  it is directed to another screen where they can choose "Yes" or "No" for signing up.  
  Clicking "Yes" takes the user to the Sign-Up Page.
- Use GridView and Adapter to display a list of product names and images.
