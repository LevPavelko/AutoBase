const daysContainer2 = document.getElementById("daysContainer2");
const prevBtn2 = document.getElementById("prevBtn2");
const nextBtn2 = document.getElementById("nextBtn2");
const monthYear2 = document.getElementById("monthYear2");
const dateInput2 = document.getElementById("dateInput2");
const calendar2 = document.getElementById("calendar2");

let currentDate2 = new Date();
let selectedDate2 = null;

function handleDayClick2(day) {
    selectedDate2 = new Date(
        currentDate2.getFullYear(),
        currentDate2.getMonth(),
        day
    );
    const formattedDate = selectedDate2.toISOString().split("T")[0];
    dateInput2.value = formattedDate;
    calendar2.style.display = "none";
    renderCalendar2();
}

function createDayElement2(day) {
    const date = new Date(currentDate2.getFullYear(), currentDate2.getMonth(), day);
    const dayElement = document.createElement("div");
    dayElement.classList.add("day");

    if (date.toDateString() === new Date().toDateString()) {
        dayElement.classList.add("current");
    }
    if (selectedDate2 && date.toDateString() === selectedDate2.toDateString()) {
        dayElement.classList.add("selected");
    }

    dayElement.textContent = day;
    dayElement.addEventListener("click", () => {
        handleDayClick2(day);
    });
    daysContainer2.appendChild(dayElement);
}

function renderCalendar2() {
    daysContainer2.innerHTML = "";
    const firstDay = new Date(
        currentDate2.getFullYear(),
        currentDate2.getMonth(),
        1
    );
    const lastDay = new Date(
        currentDate2.getFullYear(),
        currentDate2.getMonth() + 1,
        0
    );

    monthYear2.textContent = `${currentDate2.toLocaleString("default", {
        month: "long"
    })} ${currentDate2.getFullYear()}`;

    for (let day = 1; day <= lastDay.getDate(); day++) {
        createDayElement2(day);
    }
}

prevBtn2.addEventListener("click", () => {
    currentDate2.setMonth(currentDate2.getMonth() - 1);
    renderCalendar2();
});

nextBtn2.addEventListener("click", () => {
    currentDate2.setMonth(currentDate2.getMonth() + 1);
    renderCalendar2();
});

dateInput2.addEventListener("click", () => {
    calendar2.style.display = "block";
    positionCalendar2();
});

document.addEventListener("click", (event) => {
    if (!dateInput2.contains(event.target) && !calendar2.contains(event.target)) {
        calendar2.style.display = "none";
    }
});

function positionCalendar2() {
    const inputRect = dateInput2.getBoundingClientRect();
    calendar2.style.top = inputRect.bottom + "px";
    calendar2.style.left = inputRect.left + "px";
}

window.addEventListener("resize", positionCalendar2);

renderCalendar2();