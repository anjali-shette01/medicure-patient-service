

resource "aws_subnet" "subnet" {
  vpc_id = "vpc-0af2dc27bb58c87cc"  # Existing VPC ID
  cidr_block        = "172.31.50.0/24"
  availability_zone = "us-east-1a"  # Update as needed
}

resource "aws_security_group" "patient_sg" {
  name        = "patient-service-sg"
  description = "Allow 8081 inbound"
  vpc_id = "vpc-0af2dc27bb58c87cc" # Existing VPC ID

  ingress {
    from_port   = 8081
    to_port     = 8081
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "patient_instance" {
  ami                         = "ami-02b3c03c6fadb6e2c"  # Make sure this AMI is valid in your region
  instance_type               = "t2.micro"
  subnet_id                   = aws_subnet.subnet.id
  vpc_security_group_ids      = [aws_security_group.patient_sg.id]
  associate_public_ip_address = true
  key_name                   = "project-key" 

  tags = {
    Name = "patient-service-instance"
  }

  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              amazon-linux-extras install docker -y
              service docker start
              usermod -a -G docker ec2-user
              docker pull anjali8329/patient-service:latest
              docker run -d -p 8081:8080 anjali8329/patient-service:latest
              EOF
}
