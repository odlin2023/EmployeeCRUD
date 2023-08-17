package com.example.employeecrud.restController;




import com.example.employeecrud.entities.NewEmployee;
import com.example.employeecrud.entities.Report;
import com.example.employeecrud.entities.Ticket;
import com.example.employeecrud.repositories.NewEmployeeRepository;
import com.example.employeecrud.repositories.TicketRepository;
import com.example.employeecrud.services.AuthenticationService;
import com.example.employeecrud.services.NewEmployeeService;
import com.example.employeecrud.services.ReportService;
import com.example.employeecrud.services.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class TicketController {
    @Autowired
    private final TicketService ticketService;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final ReportService reportService;
    @Autowired
    private final NewEmployeeService newEmployeeService;

    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    private final NewEmployeeRepository newEmployeeRepository;




    @Autowired
    public TicketController(TicketService ticketService, TicketRepository ticketRepository, NewEmployeeService newEmployeeService, ReportService reportService, AuthenticationService authenticationService, NewEmployeeRepository newEmployeeRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.newEmployeeService=newEmployeeService;
        this.reportService = reportService;
        this.authenticationService = authenticationService;
        this.newEmployeeRepository = newEmployeeRepository;
    }



    @GetMapping("/ticket/add")
    public String addTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    @PostMapping("/ticket/add")
    public String addTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        // Save the ticket to the database
        ticketService.addTicket(ticket);

        // Retrieve the entered name from the form
        String enteredName = ticket.getName();

        // Add the entered name as a model attribute
        model.addAttribute("enteredName", enteredName);

        // Redirect to the edit ticket form
        return "redirect:/tickets" ;
    }

    @GetMapping("/ticket/edit/{id}")
    public String editTicketForm(@PathVariable Long id, Model model) {
        Optional<Ticket> optionalTicket = ticketService.getTicket(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            model.addAttribute("ticket", ticket);
            return "/edit-ticket";
        } else {
            // handle error when ticket not found
            return "error";
        }
    }


    @PostMapping("/ticket/update/{id}")
    public String updateTicketForm(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        Optional<Ticket> optionalTicket = ticketService.getTicket(id);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setStatus(ticket.getStatus());
            existingTicket.setName(ticket.getName());
            existingTicket.setDepartment(ticket.getDepartment());
            existingTicket.setPhoneNumber(ticket.getPhoneNumber());
            existingTicket.setEmail(ticket.getEmail());
            existingTicket.setAssignedTickets(ticket.getAssignedTickets());
            existingTicket.setPriority(ticket.getPriority());
            existingTicket.setDescription(ticket.getDescription());
            ticketService.updateTicket(existingTicket);
            return "redirect:/tickets";
        } else {
            return "error";
        }
    }


    @GetMapping("/ticket/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.getTicket(id);
        if (ticket.isPresent()) {
            ticketService.deleteTicket(id);
            return "redirect:/tickets";
        } else {
            // handle error when ticket not found
            return "error";
        }
    }


    @GetMapping("/chat")
    public String getChatPage() {
        return "messages";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("newEmployee", new NewEmployee());
        return "login";
    }



    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("newEmployee", new NewEmployee());
        return "registration";
    }


    @PostMapping("/registration")
    public String register(@ModelAttribute("newEmployee") NewEmployee newEmployee, RedirectAttributes redirectAttributes) {
        NewEmployee existing = newEmployeeService.findByUsername(newEmployee.getUsername());

        if (existing != null) {
            // User with the same username already exists
            redirectAttributes.addFlashAttribute("error", "Username already exists");
            return "redirect:/registration";
        }

        existing = newEmployeeService.findByEmail(newEmployee.getEmail());
        if (existing != null) {
            // User with the same email already exists
            redirectAttributes.addFlashAttribute("error", "Email already exists");
            return "redirect:/registration";
        }

        // Email and username are unique, proceed with registration
        newEmployeeService.registerNewEmployee(newEmployee);
        redirectAttributes.addFlashAttribute("success", "Registration successful");
        return "redirect:/login";
    }


    @GetMapping("/home")
    public String showForm(Model model) {
        model.addAttribute("report", new Report());
        return "home"; // the name of your Thymeleaf template
    }

    @PostMapping("/home")
    public String saveReport(@ModelAttribute Report report) {
        reportService.saveReport(report);
        return "redirect:/home";
    }

    @GetMapping("/tickets-received")
    public String showReceivedTickets(Model model) {
        Iterable<Report> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        model.addAttribute("report", new Report());
        return "tickets-received"; // the name of your Thymeleaf template
    }

    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());

        // Adding counts of tickets based on their status
        Long openTickets = ticketService.countTicketsByStatus("open");
        Long inProgressTickets = ticketService.countTicketsByStatus("in progress");
        Long closedTickets = ticketService.countTicketsByStatus("closed");

        model.addAttribute("openTickets", openTickets);
        model.addAttribute("inProgressTickets", inProgressTickets);
        model.addAttribute("closedTickets", closedTickets);

        return "tickets";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String searchTerm, Model model) {
        List<Ticket> tickets = ticketService.searchTickets(searchTerm);
        model.addAttribute("tickets", tickets);
        return "tickets";  // name of the Thymeleaf template displaying the tickets
    }





  @GetMapping("/employeeDashboard/{employeeName}")
    public String getEmployeeDashboard(@PathVariable String employeeName, Model model) {
        // Fetch tickets for the employee
        List<Ticket> tickets = ticketService.getTicketsByEmployeeName(employeeName);

        model.addAttribute("tickets", tickets);

        return "employeeDashboard"; // Or any other view name you want to use
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute NewEmployee newEmployee, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        NewEmployee existing = newEmployeeService.findByUsername(newEmployee.getUsername());

        if (existing != null && existing.getPassword().equals(newEmployee.getPassword())) {
            // Log the user's roles
            System.out.println("User roles: " + existing.getRole());

            // Set up user session
            HttpSession session = request.getSession();
            session.setAttribute("newEmployee", existing);

            String role = existing.getRole().strip().toUpperCase();
            if (role.equals("ADMIN")) {
                System.out.println("Redirecting to /tickets");
                return "redirect:/tickets";
            } else if (role.equals("USER")) {
                System.out.println("Redirecting to /employeeDashboard/" + existing.getUsername()); // Redirect to employee dashboard with the username
                return "redirect:/employeeDashboard/" + existing.getUsername();
            }
        }

        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }



    

    @GetMapping("/admin")
    public String getAdminDashboard(Model model, HttpServletRequest request, Principal principal) {
        Ticket ticket = null;
        String name = null;

        // Get the ticket from the session, if possible
        HttpSession session = request.getSession();
        if (session != null) {
            ticket = (Ticket) session.getAttribute("ticket");
            if (ticket != null) {
                name = ticket.getName();
            }
        }

        // If no session or no ticket in session, try getting ticket from Principal
        if (ticket == null && principal != null) {
            name = principal.getName();
            ticket = ticketRepository.findByName(name);
        }

        if (ticket == null) {
            // The user is not logged in. Redirect to the login page.
            return "redirect:/login";
        }

        List<Ticket> tickets = ticketRepository.findByNameContaining(name);
        model.addAttribute("ticket", ticket);
        model.addAttribute("tickets", tickets);
        model.addAttribute("role", ticket.getName()); // Add the role to the model
        return "tickets";
    }



}




