#define _CRT_SECURE_NO_WARNINGS
#define MAX_STRING 256

#include <windows.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char** argv)
{
    TCHAR ProcessName[256];
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    char command[MAX_STRING];

    if (argc != 2 ) {
        printf("ERROR\nPlease enter only 1 parameter");
        exit(1);
    }
 
    size_t convertedChars = 0;
    
    sprintf(command, "C:\\temp\\q1c.exe %s", argv[1]);
    
    mbstowcs_s(&convertedChars, ProcessName, MAX_STRING, command, _TRUNCATE);
    
    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    ZeroMemory(&pi, sizeof(pi));

    // Start the child process.
    if (!CreateProcess(NULL,   // No module name (use command line).
        ProcessName, // Command line.
        NULL,             // Process handle not inheritable.
        NULL,             // Thread handle not inheritable.
        FALSE,            // Set handle inheritance to FALSE.
        0,                // No creation flags.
        NULL,             // Use parent's environment block.
        NULL,             // Use parent's starting directory.
        &si,              // Pointer to STARTUPINFO structure.
        &pi)             // Pointer to PROCESS_INFORMATION structure.
        )
    {
        printf("CreateProcess failed (%d).\n", GetLastError());
        return -1;
    }

    // Wait until child process exits.
    WaitForSingleObject(pi.hProcess, INFINITE);

    // Close process and thread handles.
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);
}