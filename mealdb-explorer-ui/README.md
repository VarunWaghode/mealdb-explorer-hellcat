qHell Cat's Meal Explorer - Frontend

The modern, responsive user interface for the TheMealDB Explorer application. Built with React and Tailwind CSS.

Tech Stack
-----------------------------------------------------------------------
Framework: React 18
Styling: Tailwind CSS
State Management: React Hooks (useState, useEffect)
API: JavaScript Fetch API
-----------------------------------------------------------------------
* Prerequisites

Ensure your environment matches the following (or newer):
Node.js: v22.21.1 (or higher)
npm: 10.9.4 (or higher)

To check your versions, run:
-----------------
node -v
npm -v
-----------------

* How to Run

1. Navigate to the Folder
Open your terminal and move into the frontend directory:
-----------------------------------------------------------------------
cd meal-explorer-frontend
-----------------------------------------------------------------------

2. Install Dependencies

Since you are using Node v22, ensure you have a clean install:

# Optional: Remove old artifacts if they exist
rmdir /s /q node_modules
del package-lock.json

# Install dependencies
-----------------
npm install
-----------------

3. Configure Backend Connection

Open src/App.js and ensure the API URL matches your running Java Backend (usually port 8001 or 8080):

const API_URL = "http://localhost:8001/api/v1/meals";


4. Start the Application

Run the development server:
------------------
npm run dev
------------------

The app will open automatically at http://localhost:3000.

It requires the Java Backend to be running to fetch data.

Build for Production

To create an optimized build for deployment:
---------------------
npm run build
---------------------

This creates a build folder ready to be served by Nginx, Apache, or Docker.
