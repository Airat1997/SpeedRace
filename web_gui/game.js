const serverUrl = 'http://localhost:8080/state';

const canvas = document.getElementById('gameCanvas');
const gc = canvas.getContext('2d');

let field;
let score;
let highScore;
let level;
let speed;
let pause;

async function fetchState() {
    try {
        const stateResponse = await fetch(serverUrl);
        const isRunningResponse = await fetch('http://localhost:8080/state/isRunning');

        if (!stateResponse.ok || !isRunningResponse.ok) {
            throw new Error('Network response was not ok');
        }

        const stateData = await stateResponse.json();
        const isRunningData = await isRunningResponse.json();

        field = stateData.field;
        score = stateData.score;
        highScore = stateData.highScore;
        level = stateData.level;
        speed = stateData.speed;
        pause = stateData.pause;

        fieldPrint(gc);
        updateInfo();


        if (!isRunningData) {
            showGameOver();
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

function sendAction(action) {
    fetch('http://localhost:8080/actions', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(action)
    })
    .then(response => {
        if (response.ok) {
            console.log(`Action "${action}" sent successfully`);
        } else {
            console.error(`Failed to send action "${action}". Status: ${response.status}`);
        }
    })
    .catch(error => {
        console.error(`Error sending action "${action}":`, error);
    });
}

function updateInfo() {
    document.getElementById('score').textContent = score;
    document.getElementById('highScore').textContent = highScore;
    document.getElementById('level').textContent = level;
    document.getElementById('speed').textContent = speed;
    document.getElementById('pause').textContent = pause ? 'true' : 'false';
}
document.addEventListener("keydown", (event) => {
    switch (event.key) {
        case "n":
            sendAction("Start");
            break;
        case "p":
            sendAction("Pause");
            break;
        case "q":
            sendAction("Terminate");
            break;
        case "a":
            sendAction("Left");
            break;
        case "d":
            sendAction("Right");
            break;
        case "w":
            sendAction("Action");
            break;
        default:
            console.log(`Key "${event.key}" pressed, but no action assigned.`);
            break;
    }
});

function fieldPrint(gc) {
    for (let i = 0; i < 20; i++) {
        for (let j = 0; j < 9; j++) {
            if (field[i][j] === 0) {
                gc.fillStyle = 'rgb(0, 0, 0)';
                gc.fillRect(j * 50, i * 25, 50, 25);
                gc.strokeStyle = 'rgb(45, 52, 159)';
                gc.strokeRect(j * 50, i * 25, 50, 25);
            } else if (field[i][j] === 1) {
                gc.fillStyle = 'rgb(255, 243, 0)';
                gc.fillRect(j * 50, i * 25, 50, 25);
                gc.strokeStyle = 'rgb(201, 251, 181)';
                gc.strokeRect(j * 50, i * 25, 50, 25);
            } else if (field[i][j] === 2) {
                gc.fillStyle = 'rgb(255, 0, 0)';
                gc.fillRect(j * 50, i * 25, 50, 25);
                gc.strokeStyle = 'rgb(255, 255, 255)';
                gc.strokeRect(j * 50, i * 25, 50, 25);
            }
        }
    }
}
function showGameOver() {
    gc.fillStyle = 'rgba(0, 0, 0, 0.75)';
    gc.fillRect(0, 0, canvas.width, canvas.height);
    gc.fillStyle = 'white';
    gc.font = '48px Arial';
    gc.textAlign = 'center';
    gc.fillText('GAME OVER', canvas.width / 2, canvas.height / 2);
}

setInterval(fetchState, 1);

fetchState();