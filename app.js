function fetchPerformance() {
    const studentId = document.getElementById("student-id").value;
    fetch(`PerformanceServlet?student_id=${studentId}`)
        .then(response => response.json())
        .then(data => {
            const list = document.getElementById("performance-list");
            list.innerHTML = '';
            data.forEach(performance => {
                const listItem = document.createElement("li");
                listItem.textContent = `Subject ID: ${performance.subjectId}, Grade: ${performance.grade}, Semester: ${performance.semester}, Year: ${performance.year}`;
                list.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error fetching performance data:', error));
}
