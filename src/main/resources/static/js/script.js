'use strict';

// Add row numbers to the table
const rows = document.querySelectorAll('.row');
rows.forEach((row, index) => {
    let html = `<td><span>${index + 1}</span></td>`;
    row.insertAdjacentHTML('afterbegin', html);
})