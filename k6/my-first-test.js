// Import the http module to make HTTP requests. From this point, you can use `http` methods to make HTTP requests.
import http from 'k6/http';

// Import the sleep function to introduce delays. From this point, you can use the `sleep` function to introduce delays in your test script.
import { sleep } from 'k6';

export const options = {
    // Define the number of iterations for the test
    // vus: 1000,
    // duration: '1m',
    // iterations: 10,

    stages: [
        { duration: '10s', target: 1000 },  // Ramp up to 100 users in 10 seconds
        { duration: '10s', target: 2000 },  // Ramp up to 200 users in the next 10 seconds
        { duration: '10s', target: 3000 },  // Ramp up to 300 users in the next 10 seconds
        { duration: '10s', target: 4000 },  // Continue increasing
        { duration: '10s', target: 5000 },
        { duration: '10s', target: 6000 },
        { duration: '10s', target: 7000 },
        { duration: '10s', target: 8000 },
        { duration: '10s', target: 9000 },
        { duration: '10s', target: 10000 }, // Reach 1000 users
        { duration: '30s', target: 10000 }, // Hold steady at 1000 users for 30 seconds
        { duration: '10s', target: 0 },    // Ramp down to 0 users
    ],
};

// The default exported function is gonna be picked up by k6 as the entry point for the test script. It will be executed repeatedly in "iterations" for the whole duration of the test.
export default function () {
    // Make a GET request to the target URL
    // http.get('https://test-api.k6.io');
    http.get('http://localhost:8080/url-shortener/get-long-url?shortUrl=EKDlsFY');

    // Sleep for 1 second to simulate real-world usage
    sleep(1);
}