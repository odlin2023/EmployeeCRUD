$(document).ready(function() {
    // Retrieve all tickets and update the UI
    $.get('/api/tickets', function(tickets) {
        // Find the table body element
        var tableBody = $('#ticketTable tbody');

        // Clear any existing rows in the table
        tableBody.empty();

        // Iterate over the retrieved tickets
        tickets.forEach(function(ticket) {
            // Create a new table row
            var row = $('<tr>');

            // Create table data cells and populate with ticket data
            var statusCell = $('<td>').text(ticket.status);
            var nameCell = $('<td>').text(ticket.name);
            var departmentCell = $('<td>').text(ticket.department);
            var phoneNumberCell = $('<td>').text(ticket.phoneNumber);
            var emailCell = $('<td>').text(ticket.email);
            var assignedTicketsCell = $('<td>').text(ticket.assignedTickets);

            // Append the cells to the row
            row.append(statusCell, nameCell, departmentCell, phoneNumberCell, emailCell, assignedTicketsCell);

            // Append the row to the table body
            tableBody.append(row);
        });
    });

    // Create a new ticket
    $('#createTicketForm').submit(function(event) {
        event.preventDefault();

        var ticketData = {
            status: $('#createStatus').val(),
            name: $('#createName').val(),
            department: $('#createDepartment').val(),
            phoneNumber: $('#createPhoneNumber').val(),
            email: $('#createEmail').val()
        };

        $.post('/api/tickets', ticketData, function(createdTicket) {
            // Handle the response from the server (createdTicket)
            // Code to update the UI or display a success message
        });
    });

    // Update an existing ticket
    $('#editForm').submit(function(event) {
        event.preventDefault();

        var ticketData = {
            status: $('#editStatus').val(),
            name: $('#editName').val(),
            department: $('#editDepartment').val(),
            phoneNumber: $('#editPhoneNumber').val(),
            email: $('#editEmail').val()
        };

        var ticketId = // Get the ID of the ticket to be updated

            $.ajax({
                url: '/api/tickets/' + ticketId,
                method: 'PUT',
                data: JSON.stringify(ticketData),
                contentType: 'application/json',
                success: function(updatedTicket) {
                    // Handle the response from the server (updatedTicket)
                    // Code to update the UI or display a success message
                }
            });
    });

    // Delete a ticket
    $('.delete-button').click(function() {
        var ticketId = // Get the ID of the ticket to be deleted

            $.ajax({
                url: '/api/tickets/' + ticketId,
                method: 'DELETE',
                success: function() {
                    // Handle the response from the server
                    // Code to update the UI or display a success message
                }
            });
    });
});
