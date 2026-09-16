# Synctis
Synchronize your Untis Calendar into your Google Calendar.

## Running with Docker Compose

1. **Prepare configuration**:
   Copy the example environment file:
   ```bash
   cp .env.example .env
   ```
   Edit `.env` and fill in your Untis and Google credentials.

2. **Start the service**:
   ```bash
   docker compose up -d
   ```
   *(Or build from source locally: `docker compose up -d --build`)*

3. **Authenticate with Google**:
   On first run, open `http://localhost:8080/login` in your browser (or your configured `HOST:PORT`) to authorize Synctis with your Google account.
   Once authorized, credentials are saved to the persistent volume (`synctis-data`), and calendar synchronization will begin automatically according to `RUN_SCHEDULE`.

## Running with Docker Standalone

You can also run Synctis using Docker directly:
```bash
docker run -d \
  --name synctis \
  --restart unless-stopped \
  --env-file .env \
  -p 8080:8080 \
  -v synctis-data:/data \
  ghcr.io/mommde/synctis:latest
```

## Configuration
Each instance can only provide synchronization for one Calendar.  
Configuration is done via environment variables (or `.env` file).  
Take into account that every time references to the Time Zone of the Calendar.  

**RUN_SCHEDULE**: When a synchronization takes place. Written in a Crontab format. See https://crontab.guru to build your own. Defaults to `"0 5 1-31 * *"`, which means that it runs every day at 5 in the morning. (Optional)  

**WEEKS_IN_FUTURE**: How many weeks in the future the calendar synchronizes. When `RUN_SCHEDULE` invokes, all events from today until today + `WEEKS_IN_FUTURE` weeks will be copied over. Defaults to `2`. (Optional)     

**USER_AGENT**: How the HTTP client will present itself. (Optional)  

**DEBUG**: Defaults to `false`, prints extra debug messages when set to `true`. (Optional)  

**PORT**: On which port the Google Authorization Flow will take place. Defaults to `8080`. (Optional)  

**HOST**: Hostname or domain for the Google OAuth callback redirect URL (as reachable by your browser). Defaults to `localhost`. (Optional)  

**HOST_KTOR**: Host/interface address that the Ktor server binds to. Defaults to `0.0.0.0` in Docker / Docker Compose, `localhost` for bare-metal runs. (Optional)  

**GOOGLE_AUTH_FILE**: Path to the file storing Google OAuth credentials. Automatically updated when tokens refresh. Defaults to `/data/.google-auth` in Docker/Compose, `.google-auth` for local runs. (Optional)  

**GOOGLE_CLIENT_ID**: Your Google OAuth2 Client ID. (Required)  

**GOOGLE_CLIENT_SECRET**: The matching Secret to `GOOGLE_CLIENT_ID`. (Required)  

**GOOGLE_CALENDAR_ID**: Target Google Calendar ID where events will be written. Can be obtained via Google Calendar settings. (Required)  

**GOOGLE_DEFAULT_EVENT_TYPE**: How events will be presented in Google Calendar (`DEFAULT`, `FOCUSTIME`, `OUTOFOFFICE`, `WORKINGLOCATION`). Defaults to `FOCUSTIME`. (Optional)  

**GOOGLE_DEFAULT_COLOR_ID**: Google Calendar event color ID. (Optional)  

**UNTIS_SERVER**: The WebUntis server domain your school uses (e.g. `archetype.webuntis.com`). (Required)  

**UNTIS_USERNAME**: Your WebUntis username. (Required)  

**UNTIS_PASSWORD**: Your WebUntis password. (Required)  

**UNTIS_SCHOOL**: Your WebUntis school login name. (Required)  

**UNTIS_SCHOOL_LOCATION**: Details included in the `Location` field provided to Google Calendar. (Optional)  

## Google Calendar
To be Documented
